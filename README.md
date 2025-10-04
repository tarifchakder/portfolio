# Tarif Chakder — Portfolio

A personal portfolio website for Md Tarif Chakder. This is a simple, performant static site built with HTML, CSS, and vanilla JavaScript and deployed via GitHub Pages (custom domain configured via CNAME).

## Overview
- Tech stack: HTML5, CSS3, vanilla JavaScript
- Entry point: `index.html`
- Assets: styles in `assets/css/style.css`, scripts in `assets/js/script.js`, images in `assets/images/`
- Hosting: GitHub Pages with custom domain `tarifchakder.com` (see `CNAME`)
- License: Apache License 2.0 (see `LICENSE`)

## Requirements
Because this is a static site, requirements are minimal:
- A modern web browser (Chrome, Edge, Firefox, Safari)
- Optionally, a simple static HTTP server for local development (examples below). No package manager is required.

## Getting Started
### Clone
```
git clone https://github.com/tarifchakder/portfolio.git
cd portfolio
```

### Run locally
You can open `index.html` directly in a browser, or serve the site with a lightweight static server to ensure relative paths and routing behave as expected.

- Python 3
```
python3 -m http.server 8080
# then open http://localhost:8080
```

- Node (serve)
```
npm -g install serve
serve -l 8080 .
# then open http://localhost:8080
```

- PowerShell (Windows), simple server
```
powershell -Command "Start-Process http://localhost:8080; python -m http.server 8080"
```

## Scripts
There is no package.json in this repository. The client-side behavior is implemented in `assets/js/script.js`:
- Sidebar toggle and page navigation
- Filter controls for portfolio items
- Simple form validation enable/disable
- Title text cycling animation
- External blog link handling (opens blog in a new tab)
- "Coming soon" modal behavior

## Environment Variables
- None are required for local development or deployment.
- TODO: Document any future configurable settings if introduced (e.g., analytics keys).

## Tests
- There are currently no automated tests for this static site.
- TODO: Consider adding basic link checking, HTML validation, or Lighthouse audits via GitHub Actions.

## Project Structure
```
/ (repo root)
├─ CNAME                          # Custom domain for GitHub Pages (tarifchakder.com)
├─ LICENSE                        # Apache-2.0 license
├─ README.md                      # This file
├─ index.html                     # Site entry point
└─ assets/
   ├─ css/
   │  └─ style.css               # Site styles
   ├─ js/
   │  └─ script.js               # Client-side interactions
   ├─ images/                    # Images and icons
   └─ files/
      └─ tarif_chakder_resume.pdf
```

## Deployment
This repo is set up for GitHub Pages.
- The presence of `CNAME` configures the site to use `tarifchakder.com`.
- Typical deployment flows:
  - User/organization site: push to the `main` (or `master`) branch and enable Pages in the repository settings.
  - Project site: build step is not required; Pages can serve from the root or `/docs` folder.

Note: If you fork this repository, remove or update the `CNAME` file to match your own domain, or GitHub Pages will not serve the site for your fork.

## Contributing
For small fixes (typos, style tweaks), feel free to open a PR. For larger changes, please open an issue to discuss.

## License
This project is licensed under the Apache License 2.0 — see the [`LICENSE`](./LICENSE) file for details.