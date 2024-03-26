import { APP_ID, Injectable } from '@angular/core';
import PATH_OF_API from './url';
import { Product } from '../model/product.model';
import { HttpClient, HttpParams } from '@angular/common/http';

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

  public deleteProduct(id:number){
    return this.http.delete(`${PATH_OF_API}/${id}`)
  }

  public bulkDelete(param:HttpParams){
    return this.http.put(`${PATH_OF_API}/product/bulkDelete`,param);
  }
}
