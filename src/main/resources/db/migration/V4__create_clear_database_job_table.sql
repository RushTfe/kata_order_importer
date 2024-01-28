CREATE TABLE IF NOT EXISTS clear_database_job (
	id INTEGER auto_increment NOT NULL,
	CONSTRAINT clear_database_job_pk PRIMARY KEY (id),
	CONSTRAINT clear_database_job_fk FOREIGN KEY (id) REFERENCES job (id)
);