import groovy.sql.Sql
import groovy.json.JsonSlurper
//create sql connection
def sql = Sql.newInstance("jdbc:mysql://localhost/techreads", "techreads",
    "techreads")

//table would not load because it was consistently being deleted
// delete table content
sql.execute("delete from book")

def json = new JsonSlurper()

//Adds books to dataset
def GAPI= new URL('https://www.googleapis.com/books/v1/volumes?q=Witcher')
def response = json.parse(GAPI)

def books = sql.dataSet("book")
    for(int i = 0;i<10;i++) {
        def bookInfo=response.items[i].volumeInfo;
        books.add( id: i, title: bookInfo.title, author:bookInfo.authors.join(", "), rating: bookInfo.averageRating, cover: bookInfo.imageLinks.thumbnail)
    }
