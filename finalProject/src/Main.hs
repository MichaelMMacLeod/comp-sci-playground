module Main where

import System.Random
import Control.Monad
import Data.List
import Data.Char

main :: IO ()
main = do
    imageFile <- readFile "toClassify.txt"
    synapseFile <- readFile "synapses.txt"
    let neuralNet = net inputs synapses act
        inputs = read imageFile
        synapses = read synapseFile
    putStrLn $ "Classification: " ++ show (classify (last neuralNet))

err :: Char -> [Double] -> [Double]
err target xs =
    let val = ord target - 65
        pos = if val >= 32
            then val - 6
            else val
        bestCase = replicate pos 0 ++ [1] ++ replicate (length xs - pos - 1) 0
    in apply cost bestCase xs

letters = ['A'..'Z'] ++ ['a'..'z']

apply :: (a -> a -> a) -> [a] -> [a] -> [a]
apply _ [] _ = []
apply _ _ [] = []
apply f (x:xs) (y:ys) = f x y : apply f xs ys

-- Matches the largest value in xs to the corresponding letter in the List
-- ['a'..'b'] ++ ['A'..'B']. Note that 'length xs' must be <= 52
classify xs = classify' xs (0,0) 0

-- Helper function for classify
classify' :: [Double] -> (Double, Int) -> Int -> Char
classify' [] result _ = letters !! snd result
classify' (x:xs) result pos
    | x > fst result = classify' xs (x, pos) (pos + 1)
    | otherwise = classify' xs result (pos + 1)

writeRandSynapseNet = do
    rand <- randSynapseNet [52,52,52,52] act
    writeFile "synapses.txt" (show rand)

-- Returns the value of each neuron in a neural network created with the
-- specified inputs, weights, and activation function, f.
net :: [Double] -> [[[Double]]] -> (Double -> Double) -> [[Double]]
net _ [] _ = []
net inputs (w:weights) f =
    let currentLayer = layer inputs w f
        nextLayers   = net currentLayer weights f
    in  currentLayer : nextLayers

-- Returns the value of each neuron in a neuron layer created with the specified
-- inputs, weights, and activation function, f.
layer :: [Double] -> [[Double]] -> (Double -> Double) -> [Double]
layer _ [] _ = []
layer inputs (w:weights) f =
    let currentNeuron = neuron inputs w f
        nextNeurons   = layer inputs weights f
    in  currentNeuron : nextNeurons

-- Returns the value of a neuron created with the specified inputs, weights, and
-- activaiton function, f.
neuron :: [Double] -> [Double] -> (Double -> Double) -> Double
neuron inputs weights f = f (sum (zipWith (*) inputs weights))

-- Generates a random network of synapses using the number of neurons in each
-- layer and the range of an activation function, f.
randSynapseNet :: [Int] -> (Double -> Double) -> IO [[[Double]]]
randSynapseNet (l1:l2:layers) f = do
    currentSynapseLayer <- randSynapseLayer l1 l2 f
    otherSynapseLayers <- randSynapseNet (l2:layers) f
    return $ currentSynapseLayer : otherSynapseLayers
randSynapseNet _ _ = return []

-- Generates a random layer of synapses using the number of neurons above the
-- synapse layer, the number of neurons below the synapse layer, and the range
-- of an activation function, f.
randSynapseLayer :: Int -> Int -> (Double -> Double) -> IO [[Double]]
randSynapseLayer _ 0 _ = return []
randSynapseLayer upper lower f = do
    currentSynapse <- replicateM upper (randSynapse f)
    otherSynapses <- randSynapseLayer upper (lower-1) f
    return $ currentSynapse : otherSynapses

-- Generates a random weight in the range of the activation function f.
randSynapse :: (Double -> Double) -> IO Double
randSynapse f = do
    synapse <- randomIO
    return $ f synapse

-- A logistic activation function.
act :: Floating a => a -> a
act n = 1 / (1 + exp 1 ** (-n))

-- The derivative of act.
act' :: Floating a => a -> a
act' n = act n * (1 - act n)

cost :: Floating a => a -> a -> a
cost target actual = (1/2) * (target - actual) ** 2
