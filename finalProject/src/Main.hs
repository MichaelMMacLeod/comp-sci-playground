module Main where

import Codec.Picture

main :: IO ()
main = do
    image <- readImage "whatAmI.gif"
    case image of
        Left image -> do
            classification <- classify image
            print classification
        Right image -> print "Image not recognized"

weightFile :: IO [Double]
weightFile = do
    weights <- readFile "src/weights.txt"
    return $ toDouble (lines weights)

toDouble :: [String] -> [Double]
toDouble = map read

classify i = weightFile
