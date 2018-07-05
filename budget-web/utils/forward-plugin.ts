import { Compiler } from 'webpack';
import * as path from 'path';
import * as fs from 'fs-extra';

export interface Options {
  destTemplate: String;
}

export class ForwardPlugin {
  constructor(private options: Options) {}

  apply(compiler: Compiler) {
    compiler.hooks.afterEmit.tap('forward-plugin', compilation => {
      for (const fileName in compilation.assets) {
        if (compilation.assets[fileName].emitted) {
          const src = compilation.assets[fileName].existsAt;
          const parsedPath = path.parse(src);
          const splitedDir = parsedPath.dir.split(path.sep);
          const parent = splitedDir[splitedDir.length - 1];
          const destDir = this.options.destTemplate.replace('[name]', parent);
          const dest = path.resolve(destDir, parsedPath.base);
          fs.copySync(src, dest, { overwrite: true, preserveTimestamps: false });
        }
      }
    });
  }
}
