import { Injectable } from '@angular/core';
import PATH_OF_API from './url';
import { Product } from '../model/product.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  public addNewProduct(product:FormData){
    return this.http.post(`${PATH_OF_API}/product/add`,product)
  }

  public getAllProducts(){
    return this.http.get(`${PATH_OF_API}/product/getAllProducts`)
  }
}
