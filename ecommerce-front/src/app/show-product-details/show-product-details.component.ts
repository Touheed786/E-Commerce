import { ChangeDetectorRef, Component, QueryList, ViewChildren } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../model/product.model';
import { HttpErrorResponse, HttpParams } from '@angular/common/http';

interface Student {
  id: number;
  name: string;
  age: number;
  checked:boolean;
}

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.scss']
})
export class ShowProductDetailsComponent {

  constructor(private productService:ProductService){}

  headerSelected:boolean = false;
  rowSelected:boolean = false;
  ProductData:any =[];
  selectedItems: number[] = [];
  selectedIds: number[] = [];

  

  ngOnInit(){

    this.getAllProducts()
  }

  getAllProducts(){
    this.productService.getAllProducts().subscribe((product:any)=>{
      console.log(product)
      this.ProductData = product;
    },
    (error)=>{
      console.log(error)
    })
  }

  selectAll(event: any) {
    if (event.target.checked) {
      this.rowSelected  = true;
      // Select all students
      this.selectedIds = this.ProductData.map((product: { productId: any; }) => product.productId);
      console.log(this.selectedIds)
    } else {
      this.rowSelected  = false;
      // Deselect all students
      this.selectedIds = [];
      console.log(this.selectedIds)
    }
  }

  toggleSelection(event: any, id: number) {
    if (event.target.checked) {
      // Add to selectedIds
      this.selectedIds.push(id);
    } else {
      // Remove from selectedIds
      this.selectedIds = this.selectedIds.filter(selectedId => selectedId !== id);
      this.selectedIds.splice(id,1);
      this.headerSelected = false;
    }
    if(this.ProductData.length === this.selectedIds.length){
      this.headerSelected = true;
    }else{
      this.headerSelected = false;
    }
    console.log(this.selectedIds)
  }


  deleteAll(){
    let param = new HttpParams;
    // param = param.append("includeIds",JSON.stringify(this.selectedIds));
    param = param.append("includeIds",this.selectedIds.toString());
    this.productService.bulkDelete(param).subscribe(data=>{
      console.log(data)
      this.getAllProducts();
    },(err)=>{
      console.log(err.error)
    })
    this.selectedIds = [];
  }
}
