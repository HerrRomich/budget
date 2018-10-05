import * as webpack from 'webpack';
import * as path from 'path';
import * as helpers from './config/helpers';
import * as HtmlWebpackPlugin from 'html-webpack-plugin';
import { ForwardPlugin } from './utils/forward-plugin';

const config = {
  devtool: 'inline-source-map',
  mode: 'development',
  entry: {
    polyfills: './src/polyfills.ts',
    app: './src/main.ts',
    styles: ['./src/styles.scss', './src/theme.scss']
  },
  output: {
    path: path.resolve(__dirname, './output/home'),
    filename: '[name]/[name][hash].js'
  },
  resolve: {
    extensions: ['.ts', '.js', '.css', '.scss' ]
  },
  module: {
    rules: [
      {
        test: /\.ts$/,
        loaders: [
          {
            loader: 'awesome-typescript-loader',
            options: {
              configFileName: 'tsconfig.json'
            }
          },
          'angular-router-loader',
          'angular2-template-loader'
        ]
      },
      {
        test: /\.html$/,
        loader: 'html-loader'
      },
      {
        test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/,
        loader: 'file-loader?name=assets/[name].[hash].[ext]'
      },
      {
        test: /\.css$/,
        loader: 'to-string-loader!style-loader!css-loader?sourceMap'
      },
      {
        test: /\.scss$/,
        loader: 'to-string-loader!style-loader!css-loader?sourceMap`!sass-loader?sourceMap'
      }
    ]
  },
  optimization: {
    splitChunks: {
      chunks: 'async',
      minSize: 30000,
      minChunks: 1,
      maxAsyncRequests: 5,
      maxInitialRequests: 3,
      automaticNameDelimiter: '~',
      name: true,
      cacheGroups: {
        vendors: {
          test: /[\\/]node_modules[\\/]/,
          name: 'vendor',
          chunks: 'all'
        },
        default: {
          minChunks: 2,
          priority: -20,
          reuseExistingChunk: true
        }
      }
    }
  },
  plugins: [
    new webpack.ContextReplacementPlugin(
      /angular(\\|\/)core(\\|\/)(@angular|esm5)/,
      path.resolve(__dirname, '../src')
    ),
    new HtmlWebpackPlugin({
      template: 'src/index.html',
      filename: 'index.html',
      cache: true,
      chunksSortMode: (a, b) => {
        console.log(a.id + ' <-> ' + b.id);
        if (a.id === 'polyfills') {
          return -1;
        }
        if (b.id === 'polyfills') {
          return 1;
        }
        if (a.id !== b.id) {
          return b ? 1 : -1;
        } else {
          return b.id.localeCompare(a.id);
        }
      }
    }),
    new ForwardPlugin({
      destTemplate: '../com.hrrm.budget.presentation.web.[name]/WEB-CONTENT/'
    })
  ]
} as webpack.Configuration;

export default config;
