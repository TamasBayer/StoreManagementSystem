# Store Management System

This is an application to make easier for stores, warehauses to follow orders, inventory. 
There are two different sides of this application. The first one can be use to make orders, add items to the inventory. It is more complicated. The second one is more simple and it can be use to follow and change stock in the warehaus.

When it is started the user has to give the user name (Admin1; Admin2) and the password (Admin1; Admin2) on the login screen.
Then comes up a screen where are two options. Main System, which is made for employees who need all the information about all the orders and goods in the company.
Stock System is made for workers in the warehouse, they can change stock, register items into different storages.

The main system has 6 different screens.

On the right side of the screen is the name of the user, and there can the user change between the stock app and the main app, the stock manager can be opened beside the main system also if both of them are needed in one time.
And there are the options to logout, and exit.


On the first one the user sees every item in the warehaus. (Every item, order has an own unique ID number, it is generated automaticly).
User can add, edit and delete items and search in the table.
If user clicks on one item, it will open an info screen where are more info about the item. Here can be seen also how many and in wich orders are the items orderd or sold.
In the stock table are the names of the storages and which quantity is registered in it.

On the "Orders screen" are the orders which are not delivered yet.
Here are the same options available: user can add, edit or delete order. 
If the user wants to make a new order a window comes up, and here can be set the name of the company from where the goods are bought, and the date. 
Then the user can choose which items are ordered, and how many. When it is added, it can be seen in the table. 
The user can click twice on the order, and then it is visible what is not delivered yet. 
If the company gets the items, the employee can open the order, write how many is delivered from the item, and then in which storage wants he/she to book. 
If it is booked, the stock will change, and the order will stay if it is not completely delivered.
If every item is delivered then the order will go into the ready orders. 

The sell orders window work the same way like this, but there can the user not book the items, only watch which items are sold and how many.
Pick items for a sell order can be done only with the Stock Manager app.(I will discribe it later)

In the Ready orders / Ready sell ordes screen are visible all the orders which are ready.

In the Main system is the last screen the Stock in-out. 
Here can the user manually change the quantity of items without orders, for exapmle if the stock is incorrect.
The user can book "Out" or "In" items. "Out" will take from the quantity, the "In" will add to the quantity. 
First of all must be choosed "In" or "Out" then choose which item, how many, and then storage name.
If it is booked the Stock will be changed. If the user wants to take from Stock it can't be done if the quantity in the storage is not available. 
 

Stock Manager

First screen is the inventory, here are the items, ids, names, quantitys.
The user can search between them. 

On the Stock screen are the storages, and which items and how many are registered in it. (Storage names: A01;A02;A03;B01;B02;B03;C01;C02;C03);

Stock Control window is good to change Stock. It can register items from one storage into an other. 

Last but not least there are the sell orders. If someone made a Sell order in the main system, the order ID appears here. The employe can click on them an then it will be visible which items are needed to pick an how many.
User can click on the "Stock" button, and there can be seen in which storage and how many is available. If the worker found the item, and taked out from the storage he/she can book into the Sell order.
If all the items are picked, the order will disappear from here and the sell order goes into the Ready sell orders.
