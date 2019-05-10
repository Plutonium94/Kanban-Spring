let conn = new Mongo('localhost:27017');

let db = conn.getDB('test');

db.insertOne({title: 'Fix bug 12',}, )