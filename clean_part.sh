#! /bin/bash

sed '/\tphar:\/\/\//d' $1 | sed '/\t\/vagrant\/corpus\/git\/Part\/test\//d' | sed '/\t\/vagrant\/corpus\/git\/Part\/vendor\//d' | sed '/\t\/usr\/local\/bin\/phpunit/d' 
