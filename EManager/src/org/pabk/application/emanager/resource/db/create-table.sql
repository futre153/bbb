CREATE SCHEMA IF NOT EXISTS `eman` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
CREATE TABLE IF NOT EXISTS `eman`.`recipients` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `surname` VARCHAR(64) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `main` TINYINT NOT NULL DEFAULT 0,
  `desc` VARCHAR(128) NULL,
  PRIMARY KEY (`id`, `surname`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
  CREATE TABLE IF NOT EXISTS `eman`.`rgroups` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NOT NULL,
  `u1` INT UNSIGNED NOT NULL,
  `u2` INT UNSIGNED NULL,
  `u3` INT UNSIGNED NULL,
  `u4` INT UNSIGNED NULL,
  `u5` INT UNSIGNED NULL,
  `u6` INT UNSIGNED NULL,
  `u7` INT UNSIGNED NULL,
  `u8` INT UNSIGNED NULL,
  `u9` INT UNSIGNED NULL,
  `u10` INT UNSIGNED NULL,
  `u11` INT UNSIGNED NULL,
  `u12` INT UNSIGNED NULL,
  `u13` INT UNSIGNED NULL,
  `u14` INT UNSIGNED NULL,
  `u15` INT UNSIGNED NULL,
  `u16` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `FK_RGROUP_U1_idx` (`u1` ASC),
  CONSTRAINT `FK_RGROUP_U1`
    FOREIGN KEY (`u1`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U2_idx` (`u2` ASC),
  CONSTRAINT `FK_RGROUP_U2`
    FOREIGN KEY (`u2`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U3_idx` (`u3` ASC),
  CONSTRAINT `FK_RGROUP_U3`
    FOREIGN KEY (`u3`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U4_idx` (`u4` ASC),
  CONSTRAINT `FK_RGROUP_U4`
    FOREIGN KEY (`u4`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U5_idx` (`u5` ASC),
  CONSTRAINT `FK_RGROUP_U5`
    FOREIGN KEY (`u5`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U6_idx` (`u6` ASC),
  CONSTRAINT `FK_RGROUP_U6`
    FOREIGN KEY (`u6`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U7_idx` (`u7` ASC),
  CONSTRAINT `FK_RGROUP_U7`
    FOREIGN KEY (`u7`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U8_idx` (`u8` ASC),
  CONSTRAINT `FK_RGROUP_U8`
    FOREIGN KEY (`u8`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U9_idx` (`u9` ASC),
  CONSTRAINT `FK_RGROUP_U9`
    FOREIGN KEY (`u9`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U10_idx` (`u10` ASC),
  CONSTRAINT `FK_RGROUP_U10`
    FOREIGN KEY (`u10`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U11_idx` (`u11` ASC),
  CONSTRAINT `FK_RGROUP_U11`
    FOREIGN KEY (`u11`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U12_idx` (`u12` ASC),
  CONSTRAINT `FK_RGROUP_U12`
    FOREIGN KEY (`u12`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U13_idx` (`u13` ASC),
  CONSTRAINT `FK_RGROUP_U13`
    FOREIGN KEY (`u13`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U14_idx` (`u14` ASC),
  CONSTRAINT `FK_RGROUP_U14`
    FOREIGN KEY (`u14`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U15_idx` (`u15` ASC),
  CONSTRAINT `FK_RGROUP_U15`
    FOREIGN KEY (`u15`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  INDEX `FK_RGROUP_U16_idx` (`u16` ASC),
  CONSTRAINT `FK_RGROUP_U16`
    FOREIGN KEY (`u16`)
    REFERENCES `eman`.`recipients` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`citems` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `regexp` VARCHAR(256) NOT NULL,
  `rg1` INT UNSIGNED NOT NULL,
  `rg2` INT UNSIGNED NULL,
  `rg3` INT UNSIGNED NULL,
  `rg4` INT UNSIGNED NULL,
  `rg5` INT UNSIGNED NULL,
  `rg6` INT UNSIGNED NULL,
  `rg7` INT UNSIGNED NULL,
  `rg8` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CITEMS_RG1_idx` (`rg1` ASC),
  CONSTRAINT `FK_CITEMS_RG1`
    FOREIGN KEY (`rg1`)
    REFERENCES `eman`.`rgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG2_idx` (`rg2` ASC),
  CONSTRAINT `FK_CITEMS_RG2`
  FOREIGN KEY (`rg2`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG3_idx` (`rg3` ASC), 
  CONSTRAINT `FK_CITEMS_RG3`
  FOREIGN KEY (`rg3`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG4_idx` (`rg4` ASC), 
  CONSTRAINT `FK_CITEMS_RG4`
  FOREIGN KEY (`rg4`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG5_idx` (`rg5` ASC), 
  CONSTRAINT `FK_CITEMS_RG5`
  FOREIGN KEY (`rg5`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG6_idx` (`rg6` ASC), 
  CONSTRAINT `FK_CITEMS_RG6`
  FOREIGN KEY (`rg6`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG7_idx` (`rg7` ASC), 
  CONSTRAINT `FK_CITEMS_RG7`
  FOREIGN KEY (`rg7`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT, 
  INDEX `FK_CITEMS_RG8_idx` (`rg8` ASC), 
  CONSTRAINT `FK_CITEMS_RG8`
  FOREIGN KEY (`rg8`)
  REFERENCES `eman`.`citems` (`id`)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`conds` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(16) NOT NULL,
  `priority` TINYINT NOT NULL,
  `ci1` INT UNSIGNED NOT NULL,
  `ci2` INT UNSIGNED NULL,
  `ci3` INT UNSIGNED NULL,
  `ci4` INT UNSIGNED NULL,
  `ci5` INT UNSIGNED NULL,
  `ci6` INT UNSIGNED NULL,
  `ci7` INT UNSIGNED NULL,
  `ci8` INT UNSIGNED NULL,
  `ci9` INT UNSIGNED NULL,
  `ci10` INT UNSIGNED NULL,
  `ci11` INT UNSIGNED NULL,
  `ci12` INT UNSIGNED NULL,
  `ci13` INT UNSIGNED NULL,
  `ci14` INT UNSIGNED NULL,
  `ci15` INT UNSIGNED NULL,
  `ci16` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_CONDS_CI1_idx` (`ci1` ASC),
  INDEX `FK_CONDS_CI2_idx` (`ci2` ASC),
  INDEX `FK_CONDS_CI3_idx` (`ci3` ASC),
  INDEX `FK_CONDS_CI4_idx` (`ci4` ASC),
  INDEX `FK_CONDS_CI5_idx` (`ci5` ASC),
  INDEX `FK_CONDS_CI6_idx` (`ci6` ASC),
  INDEX `FK_CONDS_CI7_idx` (`ci7` ASC),
  INDEX `FK_CONDS_CI8_idx` (`ci8` ASC),
  INDEX `FK_CONDS_CI9_idx` (`ci9` ASC),
  INDEX `FK_CONDS_CI10_idx` (`ci10` ASC),
  INDEX `FK_CONDS_CI11_idx` (`ci11` ASC),
  INDEX `FK_CONDS_CI12_idx` (`ci12` ASC),
  INDEX `FK_CONDS_CI13_idx` (`ci13` ASC),
  INDEX `FK_CONDS_CI14_idx` (`ci14` ASC),
  INDEX `FK_CONDS_CI15_idx` (`ci15` ASC),
  INDEX `FK_CONDS_CI16_idx` (`ci16` ASC),
  CONSTRAINT `FK_CONDS_CI1`
    FOREIGN KEY (`ci1`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI2`
    FOREIGN KEY (`ci2`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI3`
    FOREIGN KEY (`ci3`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI4`
    FOREIGN KEY (`ci4`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI5`
    FOREIGN KEY (`ci5`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI6`
    FOREIGN KEY (`ci6`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI7`
    FOREIGN KEY (`ci7`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI8`
    FOREIGN KEY (`ci8`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI9`
    FOREIGN KEY (`ci9`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI10`
    FOREIGN KEY (`ci10`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI11`
    FOREIGN KEY (`ci11`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI12`
    FOREIGN KEY (`ci12`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI13`
    FOREIGN KEY (`ci13`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI14`
    FOREIGN KEY (`ci14`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI15`
    FOREIGN KEY (`ci15`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_CONDS_CI16`
    FOREIGN KEY (`ci16`)
    REFERENCES `eman`.`citems` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`trgroups` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cond_id` INT UNSIGNED NULL,
  `text` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_TRGROUPS_COND_ID_idx` (`cond_id` ASC),
  CONSTRAINT `FK_TRGROUPS_COND_ID`
    FOREIGN KEY (`cond_id`)
    REFERENCES `eman`.`conds` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`emsg_recs` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(8) NOT NULL DEFAULT 'and',
  `tg1` INT UNSIGNED NOT NULL,
  `tg2` INT UNSIGNED NULL,
  `tg3` INT UNSIGNED NULL,
  `tg4` INT UNSIGNED NULL,
  `tg5` INT UNSIGNED NULL,
  `tg6` INT UNSIGNED NULL,
  `tg7` INT UNSIGNED NULL,
  `tg8` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMSG_RECS_TG1_idx` (`tg1` ASC),
  INDEX `FK_EMSG_RECS_TG2_idx` (`tg2` ASC),
  INDEX `FK_EMSG_RECS_TG3_idx` (`tg3` ASC),
  INDEX `FK_EMSG_RECS_TG4_idx` (`tg4` ASC),
  INDEX `FK_EMSG_RECS_TG5_idx` (`tg5` ASC),
  INDEX `FK_EMSG_RECS_TG6_idx` (`tg6` ASC),
  INDEX `FK_EMSG_RECS_TG7_idx` (`tg7` ASC),
  INDEX `FK_EMSG_RECS_TG8_idx` (`tg8` ASC),
  CONSTRAINT `FK_EMSG_RECS_TG1`
    FOREIGN KEY (`tg1`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG2`
    FOREIGN KEY (`tg2`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG3`
    FOREIGN KEY (`tg3`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG4`
    FOREIGN KEY (`tg4`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG5`
    FOREIGN KEY (`tg5`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG6`
    FOREIGN KEY (`tg6`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG7`
    FOREIGN KEY (`tg7`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_RECS_TG8`
    FOREIGN KEY (`tg8`)
    REFERENCES `eman`.`trgroups` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`emsg_line` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `condition` VARCHAR(64) NULL,
  `text` VARCHAR(256) NULL,
  PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS `eman`.`emsg_body` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ln1` INT UNSIGNED NOT NULL,
  `ln2` INT UNSIGNED NULL,
  `ln3` INT UNSIGNED NULL,
  `ln4` INT UNSIGNED NULL,
  `ln5` INT UNSIGNED NULL,
  `ln6` INT UNSIGNED NULL,
  `ln7` INT UNSIGNED NULL,
  `ln8` INT UNSIGNED NULL,
  `ln9` INT UNSIGNED NULL,
  `ln10` INT UNSIGNED NULL,
  `ln11` INT UNSIGNED NULL,
  `ln12` INT UNSIGNED NULL,
  `ln13` INT UNSIGNED NULL,
  `ln14` INT UNSIGNED NULL,
  `ln15` INT UNSIGNED NULL,
  `ln16` INT UNSIGNED NULL,
  `ln17` INT UNSIGNED NULL,
  `ln18` INT UNSIGNED NULL,
  `ln19` INT UNSIGNED NULL,
  `ln20` INT UNSIGNED NULL,
  `ln21` INT UNSIGNED NULL,
  `ln22` INT UNSIGNED NULL,
  `ln23` INT UNSIGNED NULL,
  `ln24` INT UNSIGNED NULL,
  `ln25` INT UNSIGNED NULL,
  `ln26` INT UNSIGNED NULL,
  `ln27` INT UNSIGNED NULL,
  `ln28` INT UNSIGNED NULL,
  `ln29` INT UNSIGNED NULL,
  `ln30` INT UNSIGNED NULL,
  `ln31` INT UNSIGNED NULL,
  `ln32` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMSG_BODY_LN1_idx` (`ln1` ASC),
  INDEX `FK_EMSG_BODY_LN2_idx` (`ln2` ASC),
  INDEX `FK_EMSG_BODY_LN3_idx` (`ln3` ASC),
  INDEX `FK_EMSG_BODY_LN4_idx` (`ln4` ASC),
  INDEX `FK_EMSG_BODY_LN5_idx` (`ln5` ASC),
  INDEX `FK_EMSG_BODY_LN6_idx` (`ln6` ASC),
  INDEX `FK_EMSG_BODY_LN7_idx` (`ln7` ASC),
  INDEX `FK_EMSG_BODY_LN8_idx` (`ln8` ASC),
  INDEX `FK_EMSG_BODY_LN9_idx` (`ln9` ASC),
  INDEX `FK_EMSG_BODY_LN10_idx` (`ln10` ASC),
  INDEX `FK_EMSG_BODY_LN11_idx` (`ln11` ASC),
  INDEX `FK_EMSG_BODY_LN12_idx` (`ln12` ASC),
  INDEX `FK_EMSG_BODY_LN13_idx` (`ln13` ASC),
  INDEX `FK_EMSG_BODY_LN14_idx` (`ln14` ASC),
  INDEX `FK_EMSG_BODY_LN15_idx` (`ln15` ASC),
  INDEX `FK_EMSG_BODY_LN16_idx` (`ln16` ASC),
  INDEX `FK_EMSG_BODY_LN17_idx` (`ln17` ASC),
  INDEX `FK_EMSG_BODY_LN18_idx` (`ln18` ASC),
  INDEX `FK_EMSG_BODY_LN19_idx` (`ln19` ASC),
  INDEX `FK_EMSG_BODY_LN20_idx` (`ln20` ASC),
  INDEX `FK_EMSG_BODY_LN21_idx` (`ln21` ASC),
  INDEX `FK_EMSG_BODY_LN22_idx` (`ln22` ASC),
  INDEX `FK_EMSG_BODY_LN23_idx` (`ln23` ASC),
  INDEX `FK_EMSG_BODY_LN24_idx` (`ln24` ASC),
  INDEX `FK_EMSG_BODY_LN25_idx` (`ln25` ASC),
  INDEX `FK_EMSG_BODY_LN26_idx` (`ln26` ASC),
  INDEX `FK_EMSG_BODY_LN27_idx` (`ln27` ASC),
  INDEX `FK_EMSG_BODY_LN28_idx` (`ln28` ASC),
  INDEX `FK_EMSG_BODY_LN29_idx` (`ln29` ASC),
  INDEX `FK_EMSG_BODY_LN30_idx` (`ln30` ASC),
  INDEX `FK_EMSG_BODY_LN31_idx` (`ln31` ASC),
  INDEX `FK_EMSG_BODY_LN32_idx` (`ln32` ASC),
  CONSTRAINT `FK_EMSG_BODY_LN1`
    FOREIGN KEY (`ln1`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN2`
    FOREIGN KEY (`ln2`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN3`
    FOREIGN KEY (`ln3`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN4`
    FOREIGN KEY (`ln4`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN5`
    FOREIGN KEY (`ln5`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN6`
    FOREIGN KEY (`ln6`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN7`
    FOREIGN KEY (`ln7`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN8`
    FOREIGN KEY (`ln8`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN9`
    FOREIGN KEY (`ln9`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN10`
    FOREIGN KEY (`ln10`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN11`
    FOREIGN KEY (`ln11`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN12`
    FOREIGN KEY (`ln12`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN13`
    FOREIGN KEY (`ln13`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN14`
    FOREIGN KEY (`ln14`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN15`
    FOREIGN KEY (`ln15`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN16`
    FOREIGN KEY (`ln16`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN17`
    FOREIGN KEY (`ln17`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN18`
    FOREIGN KEY (`ln18`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN19`
    FOREIGN KEY (`ln19`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN20`
    FOREIGN KEY (`ln20`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN21`
    FOREIGN KEY (`ln21`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN22`
    FOREIGN KEY (`ln22`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN23`
    FOREIGN KEY (`ln23`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN24`
    FOREIGN KEY (`ln24`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN25`
    FOREIGN KEY (`ln25`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN26`
    FOREIGN KEY (`ln26`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN27`
    FOREIGN KEY (`ln27`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN28`
    FOREIGN KEY (`ln28`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN29`
    FOREIGN KEY (`ln29`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN30`
    FOREIGN KEY (`ln30`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN31`
    FOREIGN KEY (`ln31`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_LN32`
  FOREIGN KEY (`ln32`)
    REFERENCES `eman`.`emsg_line` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`emsg_subj` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS `eman`.`emsg_ats` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(64) NULL,
  PRIMARY KEY (`id`));
CREATE TABLE IF NOT EXISTS `eman`.`emsg_ata` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `at1` INT UNSIGNED NULL,
  `at2` INT UNSIGNED NULL,
  `at3` INT UNSIGNED NULL,
  `at4` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMSG_ATA_AT1_idx` (`at1` ASC),
  INDEX `FK_EMSG_ATA_AT2_idx` (`at2` ASC),
  INDEX `FK_EMSG_ATA_AT3_idx` (`at3` ASC),
  INDEX `FK_EMSG_ATA_AT4_idx` (`at4` ASC),
  CONSTRAINT `FK_EMSG_ATA_AT1`
    FOREIGN KEY (`at1`)
    REFERENCES `eman`.`emsg_ats` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_ATA_AT2`
    FOREIGN KEY (`at2`)
    REFERENCES `eman`.`emsg_ats` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_ATA_AT3`
    FOREIGN KEY (`at3`)
    REFERENCES `eman`.`emsg_ats` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_ATA_AT4`
    FOREIGN KEY (`at4`)
    REFERENCES `eman`.`emsg_ats` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
CREATE TABLE IF NOT EXISTS `eman`.`emsg` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `class` VARCHAR(16) NOT NULL,
  `condition` VARCHAR(64) NULL,
  `recs_id` INT UNSIGNED NOT NULL,
  `subj_id` INT UNSIGNED NOT NULL,
  `body_id` INT UNSIGNED NOT NULL,
  `atas_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_EMSG_RESC_ID_idx` (`recs_id` ASC),
  INDEX `FK_EMSG_SUBJ_ID_idx` (`subj_id` ASC),
  INDEX `FK_EMSG_BODY_ID_idx` (`body_id` ASC),
  INDEX `FK_EMSG_ATAS_ID_idx` (`atas_id` ASC),
  CONSTRAINT `FK_EMSG_RESC_ID`
    FOREIGN KEY (`recs_id`)
    REFERENCES `eman`.`emsg_recs` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_SUBJ_ID`
    FOREIGN KEY (`subj_id`)
    REFERENCES `eman`.`emsg_subj` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_BODY_ID`
    FOREIGN KEY (`body_id`)
    REFERENCES `eman`.`emsg_body` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_EMSG_ATAS_ID`
    FOREIGN KEY (`atas_id`)
    REFERENCES `eman`.`emsg_ata` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);