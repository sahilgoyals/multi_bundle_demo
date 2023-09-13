const fs = require('fs')

const MAP_FILE = 'commonBundleIdsMap.txt'
const SECONDARY_MAP_FILE = 'secondaryBundleIdsMap.txt'
const commonFileToIdMap = {}
const secondaryFileToIdMap = {}

// Read MAP_FILE & populate commonFileToIdMap
fs.readFileSync(MAP_FILE, 'utf8').toString().split('\n').forEach((content) => {
  const contentArr = content.split(':')
  commonFileToIdMap[contentArr[0]] = parseInt(contentArr[1], 10)
})

if (fs.existsSync(SECONDARY_MAP_FILE)) {
  fs.readFileSync(SECONDARY_MAP_FILE, 'utf8').toString().split('\n').forEach((content) => {
    const contentArr = content.split(':')
    secondaryFileToIdMap[contentArr[0]] = parseInt(contentArr[1], 10)
  })
}

function getParsedModulePath(path) {
  const projectRootPath = __dirname
  return path.substr(projectRootPath.length + 1)
}

module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true
      }
    })
  },
  serializer: {
    createModuleIdFactory() {
      const newsFileToIdMap = {}
      // start from end of common bundle
      const commonFileIds = Object.values(commonFileToIdMap)
      const secondaryFileIds = Object.values(secondaryFileToIdMap)
      let nextId = (secondaryFileIds.length > 0 ? secondaryFileIds[secondaryFileIds.length - 2] : commonFileIds[commonFileIds.length - 2]) + 1
      console.log('nextId', nextId)
      if (fs.existsSync(SECONDARY_MAP_FILE)) {
        // delete file if exists
        fs.unlinkSync(SECONDARY_MAP_FILE)
      }

      return function (path) {
        const modulePath = getParsedModulePath(path)
        let moduleId = commonFileToIdMap[modulePath] || newsFileToIdMap[modulePath]
        if (typeof moduleId !== 'number') {
          console.log('modulePathmodulePath', modulePath)
          moduleId = nextId++
          newsFileToIdMap[modulePath] = moduleId
          fs.appendFileSync(SECONDARY_MAP_FILE, `${modulePath}:${moduleId}\n`)
        }
        return moduleId
      }
    },
    processModuleFilter(modules) {
      const modulePath = getParsedModulePath(modules.path)
      if (typeof commonFileToIdMap[modulePath] !== 'number') {
        return true
      }
      return false
    },
    // we don't need polyfills here as they are already part of common bundle
    // sadly, require.js polyfill would still be included :(
    // https://github.com/facebook/metro/blob/master/packages/metro/src/lib/getPrependedScripts.js
    // https://github.com/facebook/metro/blob/master/packages/metro-config/src/defaults/defaults.js
    getPolyfills: () => [],
    postProcessBundleSourcemap: ({ code, map, outFileName }) =>
      // we could have excluded require.js polyfill here
      // however this parameter is not yet implemented
      // Check: https://github.com/facebook/metro/issues/400
      // for now post-business-js-bundle script is doing this job for us
      ({
        code, map
      })

  }
}
