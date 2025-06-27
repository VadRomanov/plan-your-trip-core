-- USERS
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    telegram_id BIGINT NOT NULL UNIQUE,
    username VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    language_code VARCHAR(10),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- TRIPS
CREATE TABLE trips (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    expired BOOLEAN,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT check_start_before_end CHECK (start_date <= end_date)
);

CREATE TABLE trip_users (
    trip_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    PRIMARY KEY (trip_id, user_id),
    CONSTRAINT fk_trip FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- HOTELS
CREATE TABLE hotels (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    type VARCHAR(50), -- hotel, apartments, etc.
    name VARCHAR(255),
    address VARCHAR(500),
    check_in_date DATE,
    check_out_date DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_trip_hotel FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    CONSTRAINT check_start_before_end CHECK (check_in_date < check_out_date)
);

-- TICKETS
CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    type VARCHAR(50), -- flight, train, etc.
    departure VARCHAR(255),
    arrival VARCHAR(255),
    departure_time TIMESTAMPTZ,
    arrival_time TIMESTAMPTZ,
    file_url VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_trip_ticket FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE,
    CONSTRAINT check_start_before_end CHECK (departure_time < arrival_time)
);

-- NOTES
CREATE TABLE notes (
    id BIGSERIAL PRIMARY KEY,
    trip_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_trip_note FOREIGN KEY (trip_id) REFERENCES trips(id) ON DELETE CASCADE
);