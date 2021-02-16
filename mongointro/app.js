const mongoose = require('mongoose');
// we set the default promise engine to javascript
// this is necessary because mongo supports different types of promises
mongoose.Promise = global.Promise;
// connect with the contacts DB
const url = 'mongodb+srv://sermar:SMM74@cluster0.awtsu.mongodb.net/contacts?retryWrites=true&w=majority';

mongoose.connect(url, {
    useNewUrlParser: true,
    useCreateIndex: true,
    useUnifiedTopology: true
}).then(() => { console.log('connection succesful') }).catch((err) => {
    console.log('connection error')
});

let contactSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        minlength: 1,
        trim: true
    },
    telephone: {
        type: String,
        required: true,
        unique: true,
        trim: true,
        match: /^\d{9}$/
    },
    age: {
        type: Number,
        min: 18,
        max: 120
    }
});

let contact1 = new Contact({
    name: "Boris",
    telephone: "946112230",
    age: 49
});
// we will use a save promise to save it to the database
let p1 = contact1.save().then(result => {
    console.log("Contact added:", result);
}).catch(error => {
    console.log("ERROR adding contact:", error);

});

Promise.all([p1]).then(values => {
    mongoose.connection.close();
});


let p2 = Contact.find().then(result => {
    console.log(result);
}).catch(error => {
    console.log("ERROR:", error);
});

// we must wait for all promises to finish
// to close the connection to the database
Promise.all([p1, p2]).then(values => {
    mongoose.connection.close();
});

let p3 = Contact.find({ name: 'Boris', age: 49 }).then(result => {
    console.log(result);
}).catch(error => {
    console.log("ERROR:", error);
});

// we must wait for all promises to finish
// to close the connection to the database
Promise.all([p1, p2, p3]).then(values => {
    mongoose.connection.close();
});

let p4 = Contact.remove({ name: 'Boris' }).then(result => {
    console.log(result);
}).catch(error => {
    console.log("ERROR:", error);
});

// we must wait for all promises to finish
// to close the connection to the database
Promise.all([p1, p2, p3, p4]).then(values => {
    mongoose.connection.close();
});

let p5 = Contact.findOneAndUpdate(
    { name: 'Boris' },
    { name: 'Boris Anaya', age: 50 },
    { new: true })
    .then((result) => { console.log("Contact Updated", result) })

Promise.all([p1, p2, p3, p4, p5]).then(values => {
    mongoose.connection.close();
});

let p5 = Contact.findByIdAndUpdate('5ede78b4c5e89d072c3d1a71',
    { name: 'Boris Anaya Moreno', age: 51 }, { new: true })
    .then(result => {
        console.log("Modified contact:", result);
    }).catch(error => {
        console.log("ERROR:", error);
    });

let Contact = mongoose.model('contacts', contactSchema);