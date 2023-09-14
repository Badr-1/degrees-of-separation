# Degrees of Separation

This is a re-make of the [Small-World-phenomenon](https://github.com/Badr-1/Small-World-phenomenon).

## Description

The original project used to take a list of movies and actors who played in them,
and answer queries about the Degree of Separation between two actors.

### Sample Input Format
The original project used to take them separated by `/`, in this one I used `tsv` format.

```tsv
movie1	actor1	actor2	actor3
movie2	actor2	actor4	actor5
```

## Modifications

- [ ] make it modular, i.e., make it possible to use it with any dataset
- [ ] make it cli-based
- [ ] allow for verbose output

## Usage

load the dataset and wait for a query.
```bash
dos -d <dataset.tsv>
# or
dos --dataset <dataset.tsv>
```

load and query at the same time
```bash
dos -d <dataset.tsv> -q <query>
# or
dos --dataset <dataset.tsv> --query <query>
# query format: node1/node2
```

load and query at the same time, with verbose output
```bash
dos -d <dataset.tsv> -q <query> -v
# or
dos -d <dataset.tsv> -q <query> --verbose
```