import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'priceFormat'
})
export class PriceFormatPipe implements PipeTransform {

  transform(value: number | string): string {
    if (typeof value === 'number') {
      return `$${value.toFixed(2)}`;
    } else {
      const numericValue = parseFloat(value);
      if (!isNaN(numericValue)) {
        return `$${numericValue.toFixed(2)}`;
      }
    }
    return value;
  }

}
