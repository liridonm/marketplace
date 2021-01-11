CREATE OR REPLACE FUNCTION update_name_and_symbol_on_delete() RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.is_deleted = FALSE AND NEW.is_deleted = TRUE THEN
        UPDATE currency
        SET name    = concat(name, ' - ', now()),
            symbol    = concat(symbol, ' - ', now())
        WHERE id = NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql VOLATILE
                    COST 100;


CREATE TRIGGER on_currency_delete
    AFTER UPDATE
    ON currency
    FOR EACH ROW
EXECUTE PROCEDURE update_name_and_symbol_on_delete();


