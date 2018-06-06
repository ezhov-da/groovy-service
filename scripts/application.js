Ext.define('Files', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'name', type: 'string'}
    ]
});

var store = Ext.create('Ext.data.Store', {
    extend: 'Ext.data.Store',
    model: 'Files',
    proxy: {
        type: 'ajax',
        url: 'insListFiles.groovy'
    },
    autoLoad: true
});

var panelFileList = Ext.create('Ext.grid.Panel', {
    title: 'Список файлов',
    region: 'center',
    layout: 'fit',
    store: store,
    columns: {
        items: [
            {text: 'Файл', dataIndex: 'name'}],
        defaults: {
            flex: 1
        }

    },
});


var formCreateRewriteFile = Ext.create('Ext.form.Panel', {
    title: 'Создать/отредактировать файл',
    region: 'east',
    width: '40%',
    layout: 'border',
    url: 'insFileSaver.groovy',
    items: [
        {
            region: 'north',
            fieldLabel: 'Название файла:',
            xtype: 'textfield',
            name: 'name',
            allowBlank: false
        }, {
            region: 'center',
            fieldLabel: 'Текст файла:',
            xtype: 'textarea',
            name: 'text',
            allowBlank: false
        }, {
            region: 'south',
            xtype: 'label',
        }

    ],
    buttons: [
        {
            text: 'Выполнить',
            formBind: true, //only enabled once the form is valid
            disabled: true,
            handler: function () {
                var form = formCreateRewriteFile.getForm();
                if (form.isValid()) {
                    form.submit({
                        success: function (form, action) {
                            formCreateRewriteFile.showInfo(form, action);
                        },
                        failure: function (form, action) {
                            formCreateRewriteFile.showInfo(form, action);
                        }
                    });
                }
            }
        }]
    ,
    showInfo: function (form, action) {
        console.log(action);
        formCreateRewriteFile.items.items[2].setText(action.response.responseText);
        store.reload();
    }
});

var basicPanel = Ext.create('Ext.panel.Panel', {
    title: 'Админка',
    layout: 'border',
    items: [
        panelFileList,
        formCreateRewriteFile
    ]
});

Ext.application({
    name: 'Groovy service',
    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [basicPanel]
        });
    }
});