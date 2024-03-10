import { Directive, EventEmitter, HostBinding, HostListener, Output } from '@angular/core';
import { FileHandle } from './model/file-handel.model';
import { DomSanitizer } from '@angular/platform-browser';

@Directive({
  selector: '[appDrag]'
})
export class DragDirective {


  @Output() files: EventEmitter<FileHandle> = new EventEmitter();

  constructor(private sanitizer:DomSanitizer) { }

  @HostBinding("style.background") private background = "#eee"

  @HostListener("dragover", ["$event"])
  public onDragOver(event: DragEvent)
  {
      event.preventDefault();
      event.stopPropagation();
      this.background = "#999";
  }

  @HostListener("dragleave", ["$event"])
  public onDragLeave(event: DragEvent)
  {
    event.preventDefault();
    event.stopPropagation();
    this.background = "#eee"

  }

  @HostListener("drop", ["$event"])
  public onDrop(event: any)
  {
    event.preventDefault();
    event.stopPropagation();
    this.background = "#eee" 

    // let fileHandle: FileHandle = null as any;
    const file = event.dataTransfer?.files[0];
    const fileHandle:FileHandle = {
      file:file,
      url: this.sanitizer.bypassSecurityTrustUrl(
        window.URL.createObjectURL(file)
      )
    }
    // const file = event.target.files[0];
    const url =  this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(file as any));
    // fileHandle = {file,url};
    this.files.emit(fileHandle);
  }

}
