import { FileHandle } from "./file-handel.model"

export class Product{
    productName:string
    productDescription:string
    productDiscountedPrice:number
    actaulPrice:number
    productImages:FileHandle[]
}
