import { Component } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../model/product.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.scss']
})
export class ShowProductDetailsComponent {

  constructor(private productService:ProductService){}

  ProductData:any;

  displayedColumns: string[] = ['ID.', 'Name', 'Description', 'Discounted Price','Actual Price'];

  ngOnInit(){

    this.getAllProducts()
  }

  getAllProducts(){
    this.productService.getAllProducts().subscribe((product:any)=>{
      console.log(product)
      this.ProductData = product;
    },
    (error:HttpErrorResponse)=>{
      console.log(error)
    })
  }

}
