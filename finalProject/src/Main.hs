module Main where

import Codec.Picture

main :: IO ()
main = do
    image <- unclassified
    case image of
        Left image -> do
            classification <- classify image
            print classification
        Right image -> print "Image not recognized"

weights :: IO [Double]
weights = do
    file <- readFile "src/weights.txt"
    return $ toDouble (lines file)

unclassified :: IO (Either String DynamicImage)
unclassified = do
    image <- readImage "whatAmI.gif"
    return image


toDouble :: [String] -> [Double]
toDouble = map read

classify i = weights
