DROP TABLE IF EXISTS lease_contract;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS apartment;
DROP TABLE IF EXISTS landlord;
DROP TABLE IF EXISTS tenant;


CREATE TABLE landlord (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          phone VARCHAR(20) NOT NULL UNIQUE,
                          id_card VARCHAR(20) NOT NULL UNIQUE,
                          sex INT NOT NULL DEFAULT 1,
                          email VARCHAR(100),
                          bank_account VARCHAR(50),
                          status INT NOT NULL DEFAULT 1,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE apartment (
                           id BIGSERIAL PRIMARY KEY,
                           landlord_id BIGINT NOT NULL,
                           community_name VARCHAR(100) NOT NULL,
                           address VARCHAR(200) NOT NULL,
                           city VARCHAR(50) NOT NULL,
                           district VARCHAR(50),
                           floor INT,
                           total_floor INT,
                           area DECIMAL(8,2),
                           room_count INT,
                           hall_count INT,
                           toilet_count INT,
                           rent_type INT NOT NULL DEFAULT 0,
                           status INT NOT NULL DEFAULT 1,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (landlord_id) REFERENCES landlord(id)
);

CREATE TABLE room (
                      id BIGSERIAL PRIMARY KEY,
                      apartment_id BIGINT NOT NULL,
                      room_number VARCHAR(20) NOT NULL,
                      area DECIMAL(6,2),
                      room_type VARCHAR(20),
                      monthly_rent DECIMAL(10,2) NOT NULL,
                      deposit DECIMAL(10,2) NOT NULL,
                      status INT NOT NULL DEFAULT 0,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);

CREATE TABLE tenant (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        phone VARCHAR(20) NOT NULL UNIQUE,
                        id_card VARCHAR(20) NOT NULL UNIQUE,
                        sex INT NOT NULL DEFAULT 1,
                        email VARCHAR(100),
                        emergency_contact VARCHAR(50),
                        emergency_phone VARCHAR(20),
                        status INT NOT NULL DEFAULT 1,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lease_contract (
                                id BIGSERIAL PRIMARY KEY,
                                contract_no VARCHAR(50) NOT NULL UNIQUE,
                                room_id BIGINT NOT NULL,
                                tenant_id BIGINT NOT NULL,
                                start_date DATE NOT NULL,
                                end_date DATE NOT NULL,
                                monthly_rent DECIMAL(10,2) NOT NULL,
                                deposit DECIMAL(10,2) NOT NULL,
                                pay_day INT NOT NULL DEFAULT 1,
                                pay_cycle INT NOT NULL DEFAULT 1,
                                status INT NOT NULL DEFAULT 0,
                                sign_date DATE NOT NULL,
                                remark VARCHAR(500),
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                FOREIGN KEY (room_id) REFERENCES room(id),
                                FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);