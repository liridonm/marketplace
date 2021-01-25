CREATE OR REPLACE FUNCTION update_attribute_value_on_delete() RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.is_deleted = FALSE AND NEW.is_deleted = TRUE THEN
        UPDATE product_attribute_value
        SET value    = concat(value , ' - ', now())
        WHERE id = NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql VOLATILE
                    COST 100;


CREATE TRIGGER on_attribute_delete
    AFTER UPDATE
    ON product_attribute_value
    FOR EACH ROW
EXECUTE PROCEDURE update_attribute_value_on_delete();


