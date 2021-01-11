CREATE OR REPLACE FUNCTION update_name_on_delete() RETURNS TRIGGER AS
$$
BEGIN
    IF OLD.is_deleted = FALSE AND NEW.is_deleted = TRUE THEN
        UPDATE uom
        SET name    = concat(name, ' - ', now())
        WHERE id = NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql VOLATILE
                    COST 100;


CREATE TRIGGER on_uom_delete
    AFTER UPDATE
    ON uom
    FOR EACH ROW
EXECUTE PROCEDURE update_name_on_delete();


