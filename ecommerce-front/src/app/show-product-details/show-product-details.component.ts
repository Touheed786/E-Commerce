import { ChangeDetectorRef, Component, QueryList, ViewChildren } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../model/product.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.scss']
})
export class ShowProductDetailsComponent {

  constructor(private productService:ProductService,private cdr: ChangeDetectorRef){}

  @ViewChildren('checkbox') checkboxes: QueryList<HTMLInputElement>;
  

  items = ['Item 1', 'Item 2', 'Item 3', 'Item 4'];
  selectAllCheckboxState = false;
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
    (error)=>{
      console.log(error)
    })
  }


    toggleSelectAll(event: any) {
      // const newState = event.target.checked;
      // this.selectAllCheckboxState = newState;
      // this.checkboxes.forEach((checkbox: HTMLInputElement) => {
      //   checkbox.checked = newState;
      // });
      // this.updateChangeDetection()


      const newState = event.target.checked;

      const checkboxes = document.querySelectorAll('tbody input[type="checkbox"]');
    checkboxes.forEach((checkbox: any) => {
      checkbox.checked = event.target.checked;
    });
    this.selectAllCheckboxState = newState;
    }

    toggleChildCheckbox() {
      const allChecked = this.checkboxes.toArray().every((checkbox: HTMLInputElement) => checkbox.checked);
      console.log(allChecked)
      this.selectAllCheckboxState = allChecked;
    }
    
    updateChangeDetection() {
      this.cdr.detectChanges();
  }
}
