-- Индексы для таблицы users
CREATE INDEX idx_users_telegram_id ON users(telegram_id);

-- Индексы для trips
CREATE INDEX idx_trips_user_id ON trips(user_id);
CREATE INDEX idx_trips_start_date ON trips(start_date);

-- Индексы для hotels
CREATE INDEX idx_hotels_trip_id ON hotels(trip_id);
CREATE INDEX idx_hotels_check_in_date ON hotels(check_in_date);

-- Индексы для tickets
CREATE INDEX idx_tickets_trip_id ON tickets(trip_id);
CREATE INDEX idx_tickets_departure_time ON tickets(departure_time);

-- Индексы для notes
CREATE INDEX idx_notes_trip_id ON notes(trip_id);
