CREATE TABLE IF NOT EXISTS item_type (
	id INTEGER auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL UNIQUE,
	CONSTRAINT item_type_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS priority (
	id INTEGER auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL UNIQUE,
	CONSTRAINT priority_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS region (
	id INTEGER auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL UNIQUE,
	CONSTRAINT region_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS country (
	id INTEGER auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL UNIQUE,
	region_id INTEGER NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY (id),
	CONSTRAINT region_fk FOREIGN KEY (region_id) REFERENCES region (id)
);

CREATE TABLE IF NOT EXISTS sales_channel (
	id INTEGER auto_increment NOT NULL,
	name VARCHAR(100) NOT NULL UNIQUE,
	CONSTRAINT sales_channel_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `order` (
	id INTEGER auto_increment NOT NULL,
	`uuid` UUID NOT NULL UNIQUE,
	country_id INTEGER NOT NULL,
	item_type_id INTEGER NOT NULL,
	sales_channel_id INTEGER NOT NULL,
	priority_id INTEGER NOT NULL,
	`date` DATE NOT NULL,
	ship_date DATE NOT NULL,
	units_sold INTEGER NOT NULL,
	unit_price decimal(15,2) NOT NULL,
	unit_cost decimal(15,2) NOT NULL,
	total_revenue decimal(15,2) NOT NULL,
	total_cost decimal(15,2) NOT NULL,
	total_profit decimal(15,2) NOT NULL,
	CONSTRAINT order_pk PRIMARY KEY (id),
	CONSTRAINT country_fk FOREIGN KEY (country_id) REFERENCES country (id),
	CONSTRAINT item_type_fk FOREIGN KEY (item_type_id) REFERENCES item_type (id),
	CONSTRAINT sales_channel_fk FOREIGN KEY (sales_channel_id) REFERENCES sales_channel (id),
	CONSTRAINT priority_fk FOREIGN KEY (priority_id) REFERENCES priority (id)
);