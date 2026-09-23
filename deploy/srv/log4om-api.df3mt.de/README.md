# `/srv/log4om-api.df3mt.de`

Production stack for **http://log4om-api.df3mt.de** (HTTP).

Images are built by GitHub Actions and published to GHCR. This directory only **pulls** — never builds on the host.

```bash
cd /srv/log4om-api.df3mt.de
cp .env.example .env   # first time — or run ./install.sh
./install.sh           # pull + up --no-build
```

| Service  | Image |
|----------|--------|
| api      | `ghcr.io/df3mt/log4om-api:latest` |
| web      | `ghcr.io/df3mt/log4om-web:latest` |
| postgres | `postgres:16-alpine` |
| nginx    | `nginx:1.27-alpine` → host `:80` |

CI: push to `main` on `Log4OM2-API` / `Log4OM2-Web` (or `workflow_dispatch`).
