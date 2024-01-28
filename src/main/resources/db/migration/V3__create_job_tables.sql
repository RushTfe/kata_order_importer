CREATE TABLE IF NOT EXISTS job (
	id INTEGER auto_increment NOT NULL,
	status ENUM('waiting', 'in_process', 'finished') NOT NULL,
	created_at date NOT NULL,
	updated_at date NOT NULL,
	CONSTRAINT job_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS import_job (
	id INTEGER auto_increment NOT NULL,
	CONSTRAINT import_job_pk PRIMARY KEY (id),
	CONSTRAINT job_fk FOREIGN KEY (id) REFERENCES job (id)
);