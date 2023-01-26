# Implementation Of PLA Recovery, Apo Games case study

A software product line (SPL) is a group of products that shares a set of features. Software
product lines often have many features in common and operate using the same core
programming. Often times, due to the high costs of designing SPLs from scratch, organizations
look to recover product line architecture (PLA) after producing a number of variants.

## Implementations Steps
* Jaccard similarity between variants is calculated and its graph is drawn.
* Calls between the packages of each variant are also calculated and related diagrams are
displayed.
* Exclusive packages of each variant have been calculated and based on the percentage of
repetition of exclusive packages on all packages, a threshold is set and outliers have been
obtained based on different thresholds.


### Dataset
We used Apo-Games variants as input and analyzed their packages and created a
metadata of variants.

### Evaluation Metrics
<B>SSC:</B>Structure similarity coefficient
<B>SVC:</B>Structure variability coefficient
<B>RSC:</B>Relation similarity coefficient
<B>RVC:</B>Relation variability coefficient

## How to Run
Activate your virtual environment and run the follwing command:
```
pip install -r requirements.txt
```
then,
```
python run.py
```