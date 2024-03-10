import { Component } from '@angular/core';
import { Product } from '../model/product.model';
import { ProductService } from '../services/product.service';
import Swal from 'sweetalert2';
import { FileHandle } from '../model/file-handel.model';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent {

  constructor(private productService:ProductService,private sanitizer:DomSanitizer){}

  ProductData:Product = new Product;
  isImageEmpty:boolean;
   Toast = Swal.mixin({
    toast: true,
    position: "top-end",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.onmouseenter = Swal.stopTimer;
      toast.onmouseleave = Swal.resumeTimer;
    }
  });

  
  
  
  ngOnInit(){
   
    this.ProductData.productImages = [];
  }


  addProduct(){
    if(this.ProductData.productImages.length == 0)
    {
      this.isImageEmpty = true;
      return;
    }
    const productFormData = this.prepareFormData(this.ProductData);
    this.productService.addNewProduct(productFormData).subscribe((data:any)=>{
      Swal.fire({
        title: "Done!",
        text: "Product Created Successfully!",
        icon: "success"
      });
      // this.clear()
      console.log(data)
      this.clear()
    },(err)=>{
      this.Toast.fire({
        icon: "error",
        title: err.error
      });
      console.log(err)
    })
   
  }

  prepareFormData(product:Product):FormData{
    const formData = new FormData();
    formData.append(
      'product',
      new Blob([JSON.stringify(product)],{type: 'application/json'})
    );
    for(var i =0;i<product.productImages.length;i++)
    {
      formData.append(
        'imageFile',
        product.productImages[i].file,
        product.productImages[i].file.name
      );
    }
    
    return formData;
  }

  clear()
  {
    this.ProductData = new Product
    this.ProductData.productImages = []
    this.isImageEmpty = false;
  }

  onFileSelected(event:any)
  {
    if(event.target.files)
    {
      this.isImageEmpty = false;
      const file = event.target.files[0];
      const fileHandle:FileHandle = {
        file:file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        )
      }
      this.ProductData.productImages.push(fileHandle)
      console.log(this.ProductData.productImages)
    }
  }

  removeImage(id:number){
    this.ProductData.productImages.splice(id,1)
  }

  fileDropped(fileHandle:FileHandle)
  {
    this.ProductData.productImages.push(fileHandle);
    this.isImageEmpty = false;
  }

}
