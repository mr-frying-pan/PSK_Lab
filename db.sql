DROP TABLE IF EXISTS public.Fighters CASCADE;
CREATE TABLE public.Fighters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    power INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 0,
    phrase VARCHAR(200)
);

DROP TABLE IF EXISTS public.Taverns CASCADE;
CREATE TABLE public.Taverns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS public.Weapons CASCADE;
CREATE TABLE public.Weapons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    power INT NOT NULL DEFAULT 0,
    fighterId INT REFERENCES Fighters(id) ON UPDATE CASCADE ON DELETE SET NULL
);

DROP TABLE IF EXISTS public.TavernFighter CASCADE;
CREATE TABLE public.TavernFighter (
    fighterId INT NOT NULL REFERENCES Fighters(id) ON UPDATE CASCADE ON DELETE CASCADE,
    tavernId INT NOT NULL REFERENCES Taverns(id) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(fighterId, tavernId)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO brawl_app;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO brawl_app;

select
 fighter0_.id as id1_0_,
 fighter0_.level as level2_0_,
  fighter0_.name as name3_0_,
   fighter0_.phrase as phrase4_0_,
    fighter0_.power as power5_0_
     from Fighters fighter0_ left outer join TavernFighter taverns1_ on fighter0_.id=taverns1_.fighterId left outer join Taverns tavern2_ on taverns1_.tavernId=tavern2_.id where tavern2_.id<>?