# Sainsburys website scraper task

Full task outline and requirements found [here](https://jsainsburyplc.github.io/serverside-test/)
> Using best practice coding methods, build a Java console application that scrapes the Sainsbury’s grocery site’s - Berries, Cherries, Currants page and returns a JSON array of all the products on the page.


## Requirements
* Should scrape from a link _(https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html)_
* Should return a JSON array _(`results`)_ of all products from the Berries, Cherries, Currants page
* Should return a JSON object containing `results` and `total` fields
* Should include `gross` and `vat` subfields in the `total` field
* `gross` should be the total of all items on the page
* `vat` should be the VAT on the gross amount _(i.e. $5.00 gross includes £0.83 VAT)_
* Assume each item has the UK Standard Rate of VAT (20%) applied to it.
* Each element in JSON array should contain `title`, `unit-price`, `kcal_per_100g` amd `description` keys
* Should omit `kcal_per_100g` if calories unavailable
* Should not include cross sell items _(example of cross sell item is the Sainsbury's Klip Lock Storage Set)_
* Should scrape only the first line of `description` when spread over multiple lines
* Should show `unit_price` and `total` to 2 decimal places representing pounds and whole pence
* Should be a Java application
* Should be a console application

Example JSON:
```json
{
   "results": [
     {
       "title": "Sainsbury's Strawberries 400g",
       "kcal_per_100g": 33,
       "unit_price": 1.75,
       "description": "by Sainsbury's strawberries"
     },
     {
       "title": "Sainsbury's Blueberries 200g",
       "kcal_per_100g": 45,
       "unit_price": 1.75,
       "description": "by Sainsbury's blueberries"
     },
     {
       "title": "Sainsbury's Cherry Punnet 200g",
       "kcal_per_100g": 52,
       "unit_price": 1.5,
       "description": "Cherries"
     }

   ],
   "total": {
     "gross": 5.00,
     "vat": 0.83
   }
 }
 ```

## Build

 `.mvnw clean package`


## Run
By default the application will scrape the page provided within the brief. This can be overwritten by supplying a different page link
as a property.
 ```
 java -jar main-component/target/main-component-0.1.jar [--page-url="link"]
 ```
