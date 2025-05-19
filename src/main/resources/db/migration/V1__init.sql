-- USERS
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    telegram_id BIGINT NOT NULL UNIQUE,
    username VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    language_code VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- TRIPS
CREATE TABLE trips (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_trip FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- HOTELS
CREATE TABLE hotels (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    name VARCHAR(255),
    address VARCHAR(500),
    check_in_date DATE,
    check_out_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_trip_hotel FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);

-- TICKETS
CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    type VARCHAR(50), -- flight, train, etc.
    departure VARCHAR(255),
    destination VARCHAR(255),
    departure_time TIMESTAMP,
    arrival_time TIMESTAMP,
    ticket_file_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_trip_ticket FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);

-- NOTES
CREATE TABLE notes (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    content TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_trip_note FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);