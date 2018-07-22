create or replace table entry_category(
  id int not null auto_increment primary key,
  category_type varchar(50) not null comment 'Type of entry category. "EXPENSE" for expense categories. "INCOME" for income categories',
  name varchar(250) not null comment 'Name of an entry category',
  parent_id int comment 'Reference to the parent category in the hierarchical structure'
) comment 'Entry categories. Differs expensies and incomes. Structure is hierarchical over parent_id reference';

create or replace table countpart(
  id int not null auto_increment primary key,
  name varchar(250) not null comment 'Name of an counterparty'
) comment 'Counterparty list of values';

create or replace table basket_item(
  basket_id int not null comment 'Reference to busket entry',
  basket_order int not null comment 'Sorting order of items in the basket',
  name varchar(250) not null comment 'Name of an counterparty',
  amount decimal(12,2) not null comment 'An amount of the basket item',
  comment varchar(4000) comment 'Reference to entry category',
  category_id int comment 'Reference to entry category'
) comment 'Counterparty list of values';

create or replace table account_entry(
  id int not null auto_increment primary key,
  entry_type varchar(50) not null comment 'Type of entry. "SIMPLE" simple entry with category. "BUSKET" busket of simple entries. "TRANSFER" transfer between accounts',
  account_id int not null comment 'Reference to the account',
  entry_date timestamp not null comment 'Actual date of the entry',
  transact_date timestamp not null comment 'Date of transaction (Bank accounts write-off could be later as an actual entry date)',
  budget_period date not null comment 'Only month and year of column will be used. A reference to the budget month',
  amount decimal(12,2) not null comment 'An amount of the entry',
  comment varchar(4000) comment 'Reference to entry category',
  category_id int comment 'Reference to entry category. Used only in simple entries',
  countpart_id int comment 'Reference to counterparty. Used only in simple entries',
  transfer_contrary_id int comment 'Reference to the contrary transfer. Used only in transfers'
) comment 'Basic table for all account entries';
