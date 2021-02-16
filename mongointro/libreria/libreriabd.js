
const mongoose = require('mongoose');
const fs=require('fs');

mongoose.Promise = global.Promise;

let fichero=fs.readFileSync('./book.json');
let libros=JSON.parse(fichero);

const url ='mongodb://localhost:27017/libros'
//const url = 'mongodb+srv://gabriel:castellano22@gettingstarted.6lrca.mongodb.net/contactos?retryWrites=true&w=majority';
mongoose.connect(url, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology: true
}).then(() => { console.log('connection succeful') })
    .catch((err) => {
        console.log('connection error')
    });

//scheme
let libroSchema = new mongoose.Schema({

    title: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    author: {
        type: String,
        required: true,
        unique: true,
        trim: true,
   
    },
    img: {
        type:String,
        minlength: 1
    }
});


let Libro=mongoose.model('libros',libroSchema);
let p1
libros.forEach(book => {
    let libro=new Libro();
    libro.title=book.title;
    libro.author=book.title;
    libro.img=book.img;
    p1=libro.save().then(resultado=>{
        console.log("book is added: ",resultado);
    }).catch(err=>{
        console.log("ERROR adding book");
    });

});
Promise.all([p1]).then(values=>{
    mongoose.connection.close();
});