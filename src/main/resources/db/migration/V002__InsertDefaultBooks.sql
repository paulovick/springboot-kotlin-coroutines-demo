DO $$
BEGIN
    FOR counter IN 1..10000000 LOOP
        INSERT INTO book(id, title, publication_date) VALUES (counter::varchar(255), 'Book name', now());
    END LOOP;
END; $$