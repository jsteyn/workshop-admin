[![fair-software.eu](https://img.shields.io/badge/fair--software.eu-%E2%97%8F%20%20%E2%97%8F%20%20%E2%97%8B%20%20%E2%97%8F%20%20%E2%97%8B-orange)](https://fair-software.eu)

# workshop-admin
Admin system for carpentry workshops.

The current implementation is for demo purposes only. We may want to consider rewriting it from scratch using another technology. There is a starting point in Java at [https://github.com/jsteyn/certify](https://github.com/jsteyn/certify).

## Installation

The app uses [dash](https://dash.plotly.com), a python-based web app library.

### Prerequisites

 * [python](https://www.python.org/downloads/) (currently tested with 3.7)
 * [pipenv](https://github.com/pypa/pipenv)

### Setup

Check out this repository, then create a pipenv project and install the python dependencies:

```bash
$ git clone https://github.com/CarpenPi/workshop-admin.git
$ cd workshop-admin
$ pipenv install
$ pipenv shell
```

### Run a local server

```bash
(workshop-admin) bash-3.2$ python app.py
```
