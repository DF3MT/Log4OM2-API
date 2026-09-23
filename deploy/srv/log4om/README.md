# Deploy on host (`/srv/log4om`)

HTTP-only public site: **http://log4om.df3.mt.de**

## Layout

| Path | Role |
|------|------|
| `/srv/log4om/docker-compose.yml` | postgres + api + web + nginx |
| `/srv/log4om/.env` | secrets (not in git) |
| `/srv/log4om/nginx/log4om.conf` | reverse proxy :80 |

- Browser → nginx:80 → `web:3000`
- Next.js `/backend/*` → `api:8080` (Docker network)
- Optional: `http://log4om.df3.mt.de/api/...` → API directly

## Install (on the server as root)

```bash
# copy this folder to the host, then:
cd /path/to/deploy/srv/log4om
chmod +x install.sh
./install.sh
```

Or manually:

```bash
mkdir -p /srv/log4om/nginx
cp docker-compose.yml /srv/log4om/
cp nginx/log4om.conf /srv/log4om/nginx/
cp .env.example /srv/log4om/.env   # edit secrets
cd /srv/log4om
docker compose pull && docker compose up -d
```

## DNS

`log4om.df3.mt.de` A/AAAA → host public IP (or LAN IP if only local).

## Update images

```bash
cd /srv/log4om
docker compose pull
docker compose up -d
```

## HTTPS later

Add a second nginx/`certbot` (or Caddy) listener on 443; keep the same upstreams. Set `X-Forwarded-Proto https`.
