import * as path from 'path';

const _root = path.resolve(__dirname, '..');

export const root = (...args: string[]) => {
    return path.join.apply(path, [_root].concat(args));
};
