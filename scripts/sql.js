function initTableFromJsonObject(myObject){
	Ext.define('Gists', {
	   extend: 'Ext.data.Model',
		fields: myObject.fields
	});

	console.log("model");

	var store =  Ext.create('Ext.data.Store', {
		 model: 'Gists',
		 data : myObject.data
	 });

	 console.log("store");

	var table = Ext.create('Ext.grid.Panel', {
		title: 'Результат запроса',
		height: 200,
		width: 400,
		store: store,
		region: 'south',
		columns: myObject.columns
	});
	console.log("table");
	basicPanel.addNewTable(table);
	console.log("OK");
}

var panelQuery = Ext.create('Ext.form.Panel', {
    title: 'Запрос',
    layout: 'fit',
    region: 'center',
    height: 400,
    collapsible: true,
    split: true,
    items: [{
        xtype     : 'textareafield',
        grow      : true,
        id        : 'idRawTextArea',
        name      : 'query',
        anchor    : '100%'
    }],
    buttons:[{
    	text: 'Выполнить',
    	handler: function() {
    		panelQuery.getForm().submit({
				method: 'POST',
				url: 'http://localhost:8080/groovy/sql-generator.groovy',
				 success: function(form, action) {
					var obj = Ext.decode(action.response.responseText);
					console.log(obj);
					initTableFromJsonObject(obj);
				 },
				 failure: function(form, action) {
					var obj = Ext.decode(action.response.responseText);
					console.log(obj);
					initTableFromJsonObject(obj);
				 }
			 });
    	}
    }]
});

var basicPanel = Ext.create('Ext.panel.Panel', {
	layout: 'border',
	items: [
		panelQuery
	],

	addNewTable: function(table){
		this.remove(this.items.items[1]);
		this.add(table);
	}
});

Ext.Ajax.useDefaultXhrHeader = false;

Ext.application({
    name: 'Querys',
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [basicPanel]
        });
    }
});