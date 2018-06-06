import java.util.logging.Level
import java.util.logging.Logger

response.setContentType("application/json")

Logger.getLogger("generator").log(Level.INFO, params.toString())

println """
{
  "fields": [
    {
      "name": "firstName",
      "type": "string"
    },
    {
      "name": "lastName",
      "type": "string"
    }
  ],
  "data": [
    {
      "firstName": "Ed",
      "lastName": "Spencer"
    },
    {
      "firstName": "Tommy",
      "lastName": "Maintz"
    },
    {
      "firstName": "Aaron",
      "lastName": "Conran"
    },
    {
      "firstName": "Jamie",
      "lastName": "Avins"
    }
  ],
  "columns": [
    {
      "xtype": "rownumberer"
    },
    {
      "header": "Название",
      "flex": 1,
      "dataIndex": "firstName"
    },
    {
      "header": "Описание",
      "flex": 1,
      "dataIndex": "lastName"
    }
  ]
}
"""

//println """
//{
//fields: [{
//	name: 'firstName',
//type: 'string'
// }, {
// name: 'lastName',
// type: 'string'
// }],
//
//data : [
//{firstName: 'Ed',    lastName: 'Spencer'},
//{firstName: 'Tommy', lastName: 'Maintz'},
//{firstName: 'Aaron', lastName: 'Conran'},
//{firstName: 'Jamie', lastName: 'Avins'}
//],
//
//columns: [{
//  xtype:'rownumberer' /*нумерация строк*/
//  }, {
//  header: 'Название',
//  flex: 1,
//  dataIndex: 'firstName'
//  }, {
//  header: 'Описание',
//  flex: 1,
//  dataIndex: 'lastName',
//  }]
//}
//"""








