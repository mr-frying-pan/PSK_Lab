DROP TABLE IF EXISTS public.Fighters CASCADE;
CREATE TABLE public.Fighters (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    power INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 0,
    phrase VARCHAR(200),
    opt_lock_version INT
);

DROP TABLE IF EXISTS public.Taverns CASCADE;
CREATE TABLE public.Taverns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    opt_lock_version INT
);

DROP TABLE IF EXISTS public.Weapons CASCADE;
CREATE TABLE public.Weapons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    power INT NOT NULL DEFAULT 0,
    opt_lock_version INT,
    fighterId INT REFERENCES Fighters(id) ON UPDATE CASCADE ON DELETE SET NULL
);

-- Check what happens when moving fighters around
DROP TABLE IF EXISTS public.TavernFighter CASCADE;
CREATE TABLE public.TavernFighter (
    fighterId INT NOT NULL REFERENCES Fighters(id) ON UPDATE CASCADE ON DELETE CASCADE,
    tavernId INT NOT NULL REFERENCES Taverns(id) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(fighterId, tavernId)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO brawl_app;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO brawl_app;