CREATE TABLE "devices"
(
    "id"            SERIAL PRIMARY KEY,
    "name"          VARCHAR(255) NOT NULL UNIQUE,
    "brand"         VARCHAR(255) NOT NULL,
    "created_at"    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);