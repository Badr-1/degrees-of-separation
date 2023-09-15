# Degrees of Separation

This is a re-make of the [Small-World-phenomenon](https://github.com/Badr-1/Small-World-phenomenon).

## Description

The original project used to take a list of movies and actors who played in them,
and answer queries about the Degree of Separation between two actors.

### Sample Input Format

tab-separated values (tsv), comma-separated values (csv), or slash-separated values (ssv)
```tsv
movie1	actor1	actor2	actor3
movie2	actor2	actor4	actor5
```

```csv
movie1,actor1,actor2,actor3
movie2,actor2,actor4,actor5
```

```ssv
movie1/actor1/actor2/actor3
movie2/actor2/actor4/actor5
```

## Modifications

- [x] make it modular, i.e., make it possible to use it with any dataset
- [x] make it cli-based
- [x] allow for verbose output

## Usage

You can either:
- load the dataset and query all at once
- load the dataset and query interactively

### Load the dataset and query all at once

```bash
dos -d <dataset> -q <query>
```

### Load the dataset and query interactively

```bash
dos -d <dataset>
```

### Verbose Option

```bash
dos -d <dataset> -v
# or
dos -d <dataset> -q <query> -v
```