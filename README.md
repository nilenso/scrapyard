# scrapyard

dump your dumb product ideas

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above and postgres db installed.

[1]: https://github.com/technomancy/leiningen

## Running (on a Mac)
```
   % psql
     > create database scrapyard_development;

   % lein ragtime migrate

   % sed -i '' -e 's/jithu/<your pg user>/g' project.clj src/scrapyard/db.clj

   % lein trampoline run -m scrapyard.web 3000
```