const mongoose = require('mongoose');
mongoose.Promise = global.Promise;
//conexión
mongoose.connect('mongodb://localhost:27017/musicos', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});
//esquema
let musicosSchema = new mongoose.Schema({
    nombrereal: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    nombreart: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    premios: {
        type: int,
        required: true,
        minlength: 1,
        trim: true
    },
    discos: {
        type: int,
        required: true,
        minlength: 1,
        trim: true
    },
    fecha_nac: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    fecha_falle: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    img: {
        type: String,
        required: true,
        minlength: 1,
        unique: true,
        trim: true
    }
});

//modelo
let artista = mongoose.model('musicos', musicosSchema);

//busqueda con find
let p1 = artista.find().then(resultado => {
    let cadenaDOM = "";
    resultado.forEach(cantante => {
        cadenaDOM +=
            `<div>
                <x-box>
                    <img src="./images/${cantante.img}" height="140" width="140">
                    <x-box vertical>
                        <x-label>${cantante.nombrereal}</x-label>
                        <x-label>${cantante.nombreart}</x-label>
                        <x-label>${cantante.premios}</x-label>
                        <x-label>${cantante.discos}</x-label>
                        <x-label>${cantante.fecha_nac}</x-label>
                        <x-label>${cantante.fecha_falle}</x-label>
                    </x-box>
                </x-box>
            </div>`;
    });
    document.getElementById("wrapper").innerHTML = cadenaDOM;
}).catch(error => {
    console.log("No se encontró ningún cantante");
});

document.getElementById("btnBuscar").addEventListener('click',()=>{
    let txtBuscar = document.getElementById("txtBuscar").value
    Libro.find({title:{$regex:'*'+txtBuscar+'*'}}).then(resultado => {
        let cadenaDOM = "";
        resultado.forEach(cantante => {
            cadenaDOM +=
                `<div>
                    <x-box>
                        <img src="./images/${cantante.img}" height="140" width="140">
                        <x-box vertical>
                            <x-label>${cantante.nombrereal}</x-label>
                            <x-label>${cantante.nombreart}</x-label>
                            <x-label>${cantante.premios}</x-label>
                            <x-label>${cantante.discos}</x-label>
                            <x-label>${cantante.fecha_nac}</x-label>
                            <x-label>${cantante.fecha_falle}</x-label>
                        <x-box>
                    </x-box>
                </div>`;
        });
        document.getElementById("wrapper").innerHTML = cadenaDOM;
    }).catch(error => {
        console.log("No se ha encontrado ningun cantante");
    });


})