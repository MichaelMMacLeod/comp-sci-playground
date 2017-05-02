module Paths_finalProject (
    version,
    getBinDir, getLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "/home/mmacleod/.cabal/bin"
libdir     = "/home/mmacleod/.cabal/lib/x86_64-linux-ghc-7.10.3/finalProject-0.1.0.0-2bV2CPwd5gb4JcdI9JjX9x"
datadir    = "/home/mmacleod/.cabal/share/x86_64-linux-ghc-7.10.3/finalProject-0.1.0.0"
libexecdir = "/home/mmacleod/.cabal/libexec"
sysconfdir = "/home/mmacleod/.cabal/etc"

getBinDir, getLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "finalProject_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "finalProject_libdir") (\_ -> return libdir)
getDataDir = catchIO (getEnv "finalProject_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "finalProject_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "finalProject_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "/" ++ name)
