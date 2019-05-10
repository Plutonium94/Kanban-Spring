let conn = new Mongo('localhost:27017');
let db = conn.getDB('test');

for(let collectionName of db.getCollectionNames()) {
	db[collectionName].drop();
}