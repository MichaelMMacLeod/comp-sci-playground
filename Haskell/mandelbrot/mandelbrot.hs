import Data.Complex

main = putStrLn $ (concat (map prettyList mGrid))

mTime c z n m
    | magnitude z >= 2 || n == m = n
    | otherwise = mTime c (z * z + c) (n + 1) m

escapeTime c = mTime c 0 0 100

-- center
c  = (0, 0)
-- zoom
z  = 1
-- top left
tl = ((fst c - 2) * z, (snd c - 2) * z)
-- bottom right
br = ((fst c + 2) * z, (snd c + 2) * z)
-- x increment
xi = (fst br - fst tl) / 140
-- y increment
yi = (snd br - snd tl) / 70


grid = [[(x :+ y) | x <- [fst tl, fst tl + xi..fst br]] | y <- [snd tl, snd tl + yi..snd br]]

mGrid = map (map escapeTime) grid

makePretty n = ["$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,^`'. "!!(n `mod` 69)]

prettyList l = concat (map makePretty l) ++ "\n"