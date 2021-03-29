CREATE TABLE "public"."users" (
    "id" int4 NOT NULL,
    "email_address" varchar(255) NOT NULL,
    "first_name" varchar(255) NOT NULL,
    "last_name" varchar(255) NOT NULL,
    "password" varchar(255) NOT NULL,
    PRIMARY KEY ("email_address")
);

