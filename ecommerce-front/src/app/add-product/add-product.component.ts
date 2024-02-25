import { Component } from '@angular/core';
import { Product } from './product.model';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.scss']
})
export class AddProductComponent {

  constructor(){}

  Product:Product;
  
  ngOnInit(){}


  addProduct(){
    
  }


}
