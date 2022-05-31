drop table if exists payment;

create table `payment`
(
    `id`            char(36)   not null,
    `amount`        double     not null,
    `currency`      varchar(3) not null,
    `type`          varchar(5)   not null,
    `debtor_iban`   varchar(255) not null,
    `creditor_iban` varchar(255) not null,
    `details`       varchar(255),
    `bic_code`      varchar(255),
    primary key (`id`)
) ENGINE = InnoDB  DEFAULT CHARSET = UTF8;
